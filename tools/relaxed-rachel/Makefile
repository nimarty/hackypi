CXX = g++
CXXFLAGS = -Wall -Wextra

all: ftp_client.cpp
	mkdir -p build
	$(CXX) $(CXXFLAGS) -o build/ftp_client ftp_client.cpp

clean:
	$(RM) build/ftp_client
