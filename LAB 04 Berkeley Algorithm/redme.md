<img width="960" alt="Screenshot 2025-04-05 121049" src="https://github.com/user-attachments/assets/19775e19-235c-4f9d-9eb8-80016f9e531a" />

# ⏰ Berkeley Algorithm - Clock Synchronization (Java)

This is a simple Java implementation of the **Berkeley Algorithm** for clock synchronization in distributed systems. The master (server) node calculates the average time from all clients and synchronizes them accordingly.

---

## 📌 Problem Statement

In a distributed system, clocks on different machines may not be synchronized. The **Berkeley Algorithm** is used to synchronize time among all nodes (including the master), using averaging.

---

## 🧠 How It Works

1. **Clients** send their current local time to the **server**.
2. **Server** receives all times and calculates the **average time**.
3. Server sends the **synchronized time** back to all clients.
