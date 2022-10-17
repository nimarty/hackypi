#include <arpa/inet.h>
#include <cstring>
#include <iostream>
#include <sys/socket.h>
#include <unistd.h>

using namespace std;

#define BUFFER_SIZE 1024U

int main(int argc, char **argv)
{
    if (argc < 2)
    {
        cerr << "Pass Raspberry Pi's IP address as argument!" << endl;
        return -1;
    }

    int ctrlClient, dataClient;
    struct sockaddr_in ctrlServer, dataServer;
    char buffer[BUFFER_SIZE] = {0};

    /* establish socket for control transfer */
    if ((ctrlClient = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        cerr << "Socket creation failed!" << endl;
        return -1;
    }

    ctrlServer.sin_family = AF_INET;
    ctrlServer.sin_port = htons(IPPORT_FTP);

    if (inet_pton(AF_INET, argv[1], (void *)&ctrlServer.sin_addr) < 1)
    {
        cerr << "Invalid IP address entered!" << endl;
        return -1;
    }

    if (connect(ctrlClient, (struct sockaddr *)&ctrlServer, sizeof(ctrlServer)) < 0)
    {
        cerr << "Socket connection failed!" << endl;
        return -1;
    }

    // check if service is ready for new user
    read(ctrlClient, buffer, BUFFER_SIZE);
    // cout << buffer;
    if (atoi(buffer) != 220)
        return -1;
    memset(buffer, 0, BUFFER_SIZE);

    // send user name and receive confirmation
    const char *userMsg = "USER rachel\r\n";
    send(ctrlClient, userMsg, strlen(userMsg), 0);
    read(ctrlClient, buffer, BUFFER_SIZE);
    // cout << buffer;
    if (atoi(buffer) != 331)
        return -1;
    memset(buffer, 0, BUFFER_SIZE);

    // send password and receive confirmation
    const char *passMsg = "PASS KEjfV:ucM\"N'9<R6\r\n";
    send(ctrlClient, passMsg, strlen(passMsg), 0);
    read(ctrlClient, buffer, BUFFER_SIZE);
    // cout << buffer;
    if (atoi(buffer) != 230)
        return -1;
    memset(buffer, 0, BUFFER_SIZE);

    // request passive mode and parse response
    const char *pasvMsg = "PASV\r\n";
    send(ctrlClient, pasvMsg, strlen(pasvMsg), 0);
    read(ctrlClient, buffer, BUFFER_SIZE);
    // cout << buffer;
    if (atoi(buffer) != 227)
        return -1;

    unsigned int p1, p2, dataPort;
    sscanf(buffer, "227 Entering Passive Mode (%*u,%*u,%*u,%*u,%u,%u)", &p1, &p2);
    dataPort = ((p1 << 8) + p2);
    memset(buffer, 0, BUFFER_SIZE);

    /* establish socket for data transfer */
    if ((dataClient = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        cerr << "Socket creation failed!" << endl;
        return -1;
    }

    dataServer.sin_family = AF_INET;
    dataServer.sin_port = htons(dataPort);
    inet_pton(AF_INET, argv[1], (void *)&dataServer.sin_addr);

    if (connect(dataClient, (struct sockaddr *)&dataServer, sizeof(dataServer)) < 0)
    {
        cerr << "Socket connection failed!" << endl;
        return -1;
    }

    // request file from server
    const char *retrMsg = "RETR file.txt\r\n";
    send(ctrlClient, retrMsg, strlen(retrMsg), 0);
    read(ctrlClient, buffer, BUFFER_SIZE);
    if (atoi(buffer) == 150)
    {
        memset(buffer, 0, BUFFER_SIZE);
        read(dataClient, buffer, BUFFER_SIZE);
        cout << endl
             << buffer << endl;
        read(ctrlClient, buffer, BUFFER_SIZE);
    }
    memset(buffer, 0, BUFFER_SIZE);

    // quit FTP session
    const char *quitMsg = "QUIT\r\n";
    send(ctrlClient, quitMsg, strlen(quitMsg), 0);
    read(ctrlClient, buffer, BUFFER_SIZE);
    // cout << buffer;

    return 0;
}
