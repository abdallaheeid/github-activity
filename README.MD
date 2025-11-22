# 📦 GitHub Activity CLI
*A simple Java + Spring Boot CLI to fetch and display a user's recent GitHub activity.*

---

## 📘 Project Overview

This command-line application fetches and displays the recent activity of any GitHub user using the public GitHub Events API.

It is implemented in **Java 21** using **Spring Boot** and uses only the built-in Java `HttpClient` for HTTP requests — fully matching the project requirements.

This project is inspired by and follows the specification from:  
🔗 **https://roadmap.sh/projects/github-user-activity**

---

## 🚀 Features

- Fetch a GitHub user's **recent public activity**
- Readable activity messages (Push events, Issues, PRs, Stars, etc.)
- Handles invalid usernames and API failures gracefully
- Uses **interactive mode** to ask how many events you want  
  *(or pass it via CLI arguments)*
- Default event limit = **5**
- Zero external networking libraries (only Java's built-in HttpClient)
- Modular, extensible architecture

---

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Java HttpClient** (no external HTTP libraries)
- **Jackson** (built into Spring Boot)
- **Maven**

---

## 📥 Installation

Clone the repository:

```bash
git clone https://github.com/abdallaheeid/github-activity.git
cd github-activity
