# Java Discord Bot

A feature-rich Discord bot built with Java, designed to enhance server interaction and automate tasks. This bot leverages the **Java Discord API (JDA)** to provide seamless functionality and a variety of customizable commands.

## Getting Started

### Prerequisites

To set up and run the bot, you'll need the following installed on your system:

- [Java JDK (version 8 or higher)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/install.html) (for dependency management)
- A [Discord Bot Token](https://discord.com/developers/applications)

### Installation
1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/discord-bot-java.git
   cd discord-bot-java
   ```
2. **Install Dependencies** Use Maven to resolve dependencies:
    ```bash
   mvn install
   ```
3. Configure the Bot
   - Create a file names `.env` in the home directory
   - `DEV` environment will only upsert the slash commands into your Developer server
   - `PROD` environment will upsert the slash commands globally
     ```bash
     PREFIX="Prefix of the bot"
     TOKEN="Token of the discord bot"
     DEV_SERVER ="Developer Server ID"
     ENVIRONMENT="Environment can be 'DEV' or 'PROD'"
     ```
4. **Run the Bot** Compile and run the bot using Maven:
    ```bash
        mvn compile exec:java
    ```
   
## Acknowledgments
- [JDA (Java Discord API)](https://github.com/discord-jda/JDA)
- [Discord Developer Portal](https://discord.com/developers/docs/intro)
