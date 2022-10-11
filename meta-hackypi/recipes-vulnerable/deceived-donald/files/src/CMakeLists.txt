cmake_minimum_required(VERSION 3.10)

project(memlog)

set(CMAKE_CXX_STANDARD 17)

add_library(memfunctions SHARED 
    memfunctions/memfunctions.cpp 
    memfunctions/memfunctions.h
    )

target_include_directories(memfunctions PUBLIC memfunctions)

set_target_properties(memfunctions PROPERTIES VERSION 1.0.0 SOVERSION 1)

add_executable(memlog main.cpp)

target_link_libraries(memlog PRIVATE memfunctions)


install(TARGETS memlog DESTINATION /usr/bin)
install(TARGETS memfunctions DESTINATION /usr/lib)