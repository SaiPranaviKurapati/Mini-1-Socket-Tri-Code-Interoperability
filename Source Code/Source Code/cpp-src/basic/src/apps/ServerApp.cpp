
#include "server.hpp"
#include <csignal>
#include <iostream>
/**
 * @brief basic starting point
 *
 *      Author: gash
 */


basic::BasicServer* globalServerPtr = nullptr;

void signal_handler(int signal) {
    if (signal == SIGINT) {
        std::cout << "Stopping server..." << std::endl;
        if (globalServerPtr) {
            globalServerPtr->stop();  // Call the stop method on the server instance
        }
        std::exit(0);
    }
}


int main(int argc, char **argv) {
    //basic::BasicServer svr;
    basic::BasicServer svr("localhost", 2000);
    globalServerPtr = &svr;
    
    std::signal(SIGINT, signal_handler);
    svr.start();
    while (true) {
        std::this_thread::sleep_for(std::chrono::seconds(1));
    }
}

