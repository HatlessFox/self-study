from cancer_data import read_training_data
from matutil import rowdict2mat, mat2rowdict
import simplex
from vec import Vec
from mat import Mat

FEATUREs = {"area(worst)", "smoothness(worst)", "texture(mean)"}

### read training data
print("== Read Training Data ==")
(patient_features, diagnoses) = read_training_data("train.data", FEATUREs)
IDs = patient_features.D[0]

#### Set matrix A
### set A labels
A_COLS = FEATUREs.union(IDs)
A_COLS = A_COLS.union({"gamma"})

A_ROWS = IDs.union({-i for i in IDs})

### set A content
## Task 13.13.1
def main_constraint(i, pf_i, d_i, features):
    v = Vec(A_COLS, {f:d_i * pf_i[f] for f in features})
    v['gamma'] = -1 * d_i
    v[i] = 1
    return v

## Task 13.13.2
def make_matrix(feature_vectors, diagnoses, features):
    ids = feature_vectors.keys()

    # main constraints
    rows = {i:main_constraint(i, feature_vectors[i], diagnoses[i], features)
            for i in ids}
    # nonnegativity constraints
    rows.update({-i:Vec(A_COLS, {i:1}) for i in ids})
    return rowdict2mat(rows)

## Task 13.13.3
def make_rh_bound(ids):
    lbls = ids.union({-id for id in ids})
    return Vec(A_ROWS, {l:(1 if 0 < l else 0) for l in A_ROWS})

## Task 13.13.4
def make_objective_function(ids, features):
    return Vec(ids.union(features), {id:1 for id in ids})

## Task 13.13.5
print("== Setup LP ==")
A = make_matrix(mat2rowdict(patient_features), diagnoses, FEATUREs)
b = make_rh_bound(IDs)
c = make_objective_function(IDs, FEATUREs)

def find_classifier(A, b, c):
    n = len(A.D[1])
    R_sq = IDs.copy()
    for i in IDs:
        if n <= len(R_sq): break
        R_sq.add(-i)

    print("=== LP: Find vertex ===")
    if not simplex.find_vertex(A, b, R_sq):
        raise Error("Unable to find start vertex")
    print("=== LP: Run simplex ===")
    soln = simplex.optimize(A, b, c, R_sq)
    w = Vec(FEATUREs, {f:soln[f] for f in FEATUREs})
    gamma = soln["gamma"]
    return (w, gamma)

## Task 13.13.6

def C(feature_vector, w, gamma):
    return 1 if gamma < w*feature_vector else -1

## Task 13.13.7
def test_classifier(fname, w, gamma):
    patient_features, diagnosis = read_training_data(fname, FEATUREs)
    return sum(1 if C(features, w, gamma) == diagnoses[lbl] else 0
               for lbl, features in mat2rowdict(patient_features))

print("== Classifier Search ==")
w, gamma = find_classifier(A, b, c)

print("== Classifier Evaluation ==")
training_errors = test_classifier("train.data", w, gamma)
print("Training errors {} {.2%}".format(training_errors, 1.0*training_errors / len(IDs)))

validation_errors = test_classifier("validate.data", w, gamma)
print("Validation errors {} {.2%}".format(validation_errors, 1.0*validation_errors / len(IDs)))
