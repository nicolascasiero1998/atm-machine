# atm-machine by casierni

Pre-requisites

Docker    
Maven   
jdk 1.8 or plus   

Atm machine is nothing but a simulator of a bank counter that allows you to perform different bank actions, including withdrawal, deposit and balance.

The program has been written in full in Java and is also made available a Docker image.

It is also equipped with the system "Greedy Algorithm" which is nothing more than a calculation system based on the minimum banknote disbursement based on the maximum and minimum denomination available.

Initially two accounts are created:

Customer Number:123456789      PIN:1234

Customer Number:987654321      PIN:4321

Both initialized with 1500€ and with the following banknote denomination:

10 x €50s, 30 x €20s, 30 x €10s and 20 x €5s

The system will show this initially but not during withdrawal/deposit.

To run the program follow these instructions:  

1)git clone https://github.com/nicolascasiero1998/atm-machine.git   

2)cd atm-machine    

3)docker build -t atmimage:v1 .   

4)docker run -t -i atmimage:v1


🅴🅽🅹🅾🆈 🆃🅷🅴 🅶🅰🅼🅴!
