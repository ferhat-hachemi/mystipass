# 🔐 Mystipass

**Mystipass** is a simple and secure command-line password manager written in Java using Spring Shell.  
Store, retrieve, and manage your credentials locally with encryption.

> 🛠 Built with **[GraalVM Native Image](https://www.graalvm.org/)** for optimal performance and near-instant startup.

## 🚀 Features
- 🗂 Local filesystem storage — all data is stored in your home directory

- 🔐 Encrypted credentials — passwords are encrypted for security

- 🧪 Master password protection — secure access with a bcrypt-hashed master password

- 💻 Built-in interactive CLI — navigate with user-friendly commands

- ⚡ Fast startup time — compiled to a native binary using GraalVM for lightning-fast CLI performance

- 🐧 Packaged as `.deb` — easily installable on Debian-based systems

## 🧾 Commands

> 🔐 **Note:** Each of the commands (`add`, `get`, and `list`) prompts for your master password before execution. This ensures that only authorized user can access the stored credentials.

- ⚙️ Initialize mystipass
```bash
  mystipass init 
```

- 🗝️ Add a new credential
```bash 
  mystipass add --key <key> --username <username> --password <password>
```

- 🔍 Retrieve a credential by key
```bash 
  mystipass get --key <key>
```

- List all saved credentials
```bash 
  mystipass list
```

## 📦 Installation

### 📥 Install via `.deb` package (Debian/Ubuntu)

```bash 
  sudo dpkg -i mystipass_1.0.0_amd64.deb
```

## 🛠 Initialization

After installation, you must run the `init` command to set up the necessary files and configuration:

- 🔐 You will be prompted to set a master password, which will be securely stored with a salt in: `/home/<your-username>/.mystipass/mystipass.conf`

- 📄 Creates the encrypted credentials file: `/home/<your-username>/.mystipass/credentials` — where all your password entries are securely stored

> 🔗 The mystipass executable is globally available at `/usr/local/bin/mystipass`, so you can run it from anywhere in your terminal.

## 👨‍💻 Author

### [Ferhat Hachemi](https://www.linkedin.com/in/h-ferhat-account/)

## 🙌 Contributing

Contributions are welcome! 🎉

If you'd like to improve mystipass CLI, feel free to:

- 🛠 Open a pull request with your improvements or fixes
- 🐞 Report bugs by [opening an issue](https://github.com/ferhat-hachemi/mystipass/issues)
- 💡 Suggest new features or enhancements

Thank you for helping make mystipass better!

## 📄 License
mystipass CLI is under the MIT License. See the [LICENSE](https://github.com/ferhat-hachemi/mystipass/blob/master/LICENSE) file for details.
