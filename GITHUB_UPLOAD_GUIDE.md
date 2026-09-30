# GitHub Upload Guide (for beginners)

## Method 1 - Upload from the website (no Git needed) - EASIEST

1. Go to https://github.com and sign in (create a free account if you don't have one).
2. Click the **+** icon (top right) -> **New repository**.
3. Fill in:
   - **Repository name:** `spring-boot-bank-api`
   - **Description:** Bank Management System REST API built with Spring Boot, Spring Security, JPA and MySQL
   - Choose **Public**
   - Do **NOT** tick "Add a README", ".gitignore" or "license" (the project already has them)
4. Click **Create repository**.
5. On the next page click the link **"uploading an existing file"**.
6. **Unzip `bank.zip` first** on your computer. Open the `bank` folder.
7. Select **everything inside the `bank` folder** (`src`, `docs`, `pom.xml`, `README.md`,
   `LICENSE`, `.gitignore`) and **drag them into the GitHub page**.
   - Do not upload the zip file itself - GitHub will not extract it.
   - If you can't see `.gitignore`, turn on "Show hidden files" in your file explorer.
8. Wait until all files finish uploading.
9. In **Commit changes**, type: `Initial commit - Bank Application`
10. Click **Commit changes**. Done!

Your project link will be: `https://github.com/<your-username>/spring-boot-bank-api`

### Check after uploading
- README shows the project title and the 7 output images
- Folder `src/main/java/com/example/bank` contains controller, model, repo, security, service
- `application.properties` shows `${DB_PASSWORD:your_mysql_password}` (no real password)

## Method 2 - Using Git commands

Install Git from https://git-scm.com, then open a terminal inside the `bank` folder:
```bash
git init
git add .
git commit -m "Initial commit - Bank Application"
git branch -M main
git remote add origin https://github.com/<your-username>/spring-boot-bank-api.git
git push -u origin main
```
GitHub asks you to sign in. Use a Personal Access Token as the password
(GitHub -> Settings -> Developer settings -> Personal access tokens).

## After upload - make the repo look professional
- On the repo page click the gear icon next to **About** -> add the description and topics:
  `java`, `spring-boot`, `rest-api`, `mysql`, `spring-security`, `jpa`
- Pin the repository on your profile (Profile -> Customize your pins).

## Security reminder
Never upload your real MySQL password. This project reads it from the `DB_PASSWORD`
environment variable. If you ever pushed a real password by mistake, change it immediately.
