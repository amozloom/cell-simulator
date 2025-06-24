# 🔥🌊 Cell Simulator – Wild Fire & Water World

A JavaFX-powered cellular automata simulator that models two environmental systems: **Wild Fire** and **Water World (WaTor)**. This was a group project for Elon University's Software Design course, focusing on object-oriented design, the MVC pattern, and code extensibility.

---

## 📘 Project Overview

This simulator models 2D cellular automata in a flexible and extensible system. Our team was responsible for implementing two simulation contexts:

- 🔥 **Wild Fire** – Models forest fire spread based on probability, density, and burn time.
- 🌊 **Water World (WaTor)** – Simulates a predator-prey ecosystem of fish and sharks on a toroidal grid.

Users can configure parameters and observe how small rule changes influence large-scale patterns over time.

---

## 🛠️ Technologies Used

- Java 17+
- JavaFX
- MVC Design Pattern
- Git & GitHub (Version Control)
- JUnit (Testing)

---

## 🕹️ Simulation Features

### 🔥 Wild Fire

- **Cell States**: Empty, Tree, Burning Tree, Burnt Tree
- **Custom Parameters**:
  - `burn time` (cycles a tree burns)
  - `spread probability` (chance fire spreads to neighbors)
  - `forest density` (initial tree coverage)
  - `initial burning trees`
- **Rules**:
  - Burning trees ignite adjacent live trees with `spread probability`
  - After `burn time`, trees become burnt
- **UI Controls**: start, step, pause, and parameter entry

### 🌊 Water World (WaTor)

- **Cell States**: Water, Fish, Shark, Edge
- **Custom Parameters**:
  - `fish breed time`, `shark breed time`, `shark starve time`
  - `initial fish density`, `initial shark density`
  - Grid size (rows × columns)
- **Rules**:
  - Fish move and breed after fixed cycles
  - Sharks move, eat fish, breed, and die if they don’t eat
- **UI Controls**: start, step, pause, and parameter entry

---

## ✅ Design & Development Highlights
- Followed Model-View-Controller (MVC) architecture
- Used the Open-Closed Principle: each simulation extends an abstract Cell and defines custom behavior
- Extensible to support additional simulations (e.g., Game of Life, Rock-Paper-Scissors)
- Tested via custom JUnit tests for update logic and edge conditions
- Each team member implemented different modules with clear documentation and commit logs

---

## 🧪 Testing
Test coverage includes:
- Cell state transitions
- Probability-driven behavior
- Grid updates over multiple steps
- Breed and starvation logic in WaTor

---

## 👥 Team & Contributions
Anthony Mozloom – Wild Fire logic, burn progression, GUI integration

Quincy – Water World logic (fish & shark movement), user parameter interface

Reed – MVC integration, grid state updater, reusable abstraction for cells and rules

