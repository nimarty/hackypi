cmake_minimum_required(VERSION 3.12)

project(moody-maggie VERSION 1.0 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)

add_executable(${PROJECT_NAME} bind-shell.cpp)

target_compile_options(${PROJECT_NAME} PRIVATE -Wall -Wextra)
