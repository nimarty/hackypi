cmake_minimum_required(VERSION 1.9)
project (hems_cli)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fno-stack-protector -z execstack")
add_executable(hems_cli main.cpp)
install(TARGETS hems_cli RUNTIME DESTINATION bin)

