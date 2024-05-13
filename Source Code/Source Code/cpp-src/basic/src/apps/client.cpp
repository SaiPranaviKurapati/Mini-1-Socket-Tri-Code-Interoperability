
#include <thread>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <unistd.h>
#include <sstream>
#include <iostream>

#include "client.hpp"
#include "basicbuilder.hpp"

basic::BasicClient::BasicClient(std::string name, std::string ipaddr, unsigned int port) {
      this->name = name;
      this->group = "public";
      this->ipaddr =ipaddr;
      this->portN = port;
      this->good = false;
      this->clt = -1;

      if (this->portN <= 1024)
         throw std::out_of_range("port must be greater than 1024");
}

void basic::BasicClient::stop() {
   std::cerr << "--> closing client connection" << std::endl;
   this->good = false;

   if (this->clt > -1) {
      ::close(this->clt);
      this->clt = -1;
   }
}

void basic::BasicClient::join(std::string group){
   this->group = group;
}

void basic::BasicClient::sendMessage(std::string m) {
   if (!this->good) return;

   basic::Message msg(this->name, this->group, m);
   basic::BasicBuilder bldr;
   auto payload = bldr.encode(msg);
   auto plen = payload.length();
   size_t sent = 0; // Track the amount of data sent

   // Record start time
   auto start_time = std::chrono::steady_clock::now(); 

   while (this->good && sent < plen) {
      auto n = ::write(this->clt, payload.c_str() + sent, plen - sent);

      if (n == -1) {
            std::cerr << "--> send() error for " << m << ", n = " << n << ", errno = " << errno << std::endl;
            if (errno == ETIMEDOUT) {
               throw std::runtime_error("operation timed out.");
            }
            // For other errors, we might not want to continue attempting to send
            break;
      } else if (n == 0) {
            // No data was sent, and it's unclear why. Handle as an error or a retry condition based on your needs.
            throw std::runtime_error("No data sent, connection may be closed.");
      } else if (static_cast<size_t>(n) < plen - sent) {
            // Partial send, update sent count and continue loop to attempt to send remainder
            std::cerr << "Partial send, " << n << " bytes sent, attempting to send remainder." << std::endl;
            sent += n;
            // No immediate action needed here; loop will attempt to send remainder
      } else {
            std::cerr << "sent: " << payload << ", size: " << plen << ", errno: " << errno << std::endl;
            sent += n; // Ensure sent is updated to exit loop
      }
   }

   auto end_time = std::chrono::steady_clock::now(); // Record end time
   std::chrono::duration<double> transmission_time = end_time - start_time;
   
   if (sent < plen) {
        // Not all data was sent, handle this as a problem
      std::stringstream err;
      err << "failed to fully send, sent " << sent << " of " << plen << ", err = " << errno << std::endl;
      throw std::runtime_error(err.str());
   } else {
      std::cerr << "Transmission time: Message sent successfully in " << transmission_time.count() << " seconds." << std::endl;
   }
}


void basic::BasicClient::connect() {
   if (this->good) return;

   std::cerr << "connecting..." << std::endl;
   auto start_time = std::chrono::steady_clock::now(); // Record start time

   this->clt = ::socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
   if (this->clt < 0) {
      std::stringstream err;
      err << "failed to create socket, err = " << errno << std::endl;
      throw std::runtime_error(err.str());
   }

   struct sockaddr_in serv_addr;
   serv_addr.sin_family = AF_INET;
   serv_addr.sin_addr.s_addr = inet_addr(this->ipaddr.c_str());
   serv_addr.sin_port = htons(this->portN);

   auto stat = inet_pton(AF_INET, this->ipaddr.c_str(), &serv_addr.sin_addr);
   if (stat < 0) {
      throw std::runtime_error("invalid IP address");
   }

   stat = ::connect(this->clt, (struct sockaddr*)&serv_addr, sizeof(serv_addr));

   auto end_time = std::chrono::steady_clock::now(); // Record end time
   std::chrono::duration<double> connection_time = end_time - start_time;
   std::cerr << "Connection time: attempt took " << connection_time.count() << " seconds." << std::endl;

   if (good) {
      std::cerr << "Connected to server." << std::endl;
   }
   //  else {
   //    std::cerr << "Failed to connect to server." << std::endl;
   // }

   if (stat < 0) {
      std::stringstream err;
      err << "failed to connect() to server, err = " << errno << std::endl;
      throw std::runtime_error(err.str());
   }

   this->good = true;
}

