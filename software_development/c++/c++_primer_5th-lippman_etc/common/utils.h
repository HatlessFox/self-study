#ifndef CXX_PRIMER_5TH_COMMON_UTILS_H
#define CXX_PRIMER_5TH_COMMON_UTILS_H

#include <string>

template <typename T>
std::string get_type() {
  // TODO: extract "type" part form the function name.
  return __PRETTY_FUNCTION__;
}

#endif
