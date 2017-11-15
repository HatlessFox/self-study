(defparameter *wizard-nodes*
  '((living-room (you are in the living-room.
                  a wizard is snoring loudly on the couch.))
    (garden      (you are in the beautiful garden.
                  there is a well in front of you.))
    (attic       (you are in the attic.
                  thire is a giant welding tourch in the corner.))))

(defparameter *wizard-edges*
  '((living-room (garden west door)
                 (attic upstairs ladder))
    (garden (living-room east door))
    (attic (living-room downstairs ladder))))
