# Contributing to Lord of the Thesis 🌋

Thank you for your interest in contributing to **Lord of the Thesis**! 

This document provides guidelines for contributing to the project.

---

## 🤝 How to Contribute

### Reporting Bugs 🐛

If you find a bug, please create an issue on GitHub with:
- **Description**: What happened?
- **Steps to Reproduce**: How can we reproduce the bug?
- **Expected Behavior**: What should have happened?
- **Environment**: OS, Java version, etc.
- **Screenshots**: If applicable

### Suggesting Features 💡

Have an idea for a new feature? Create an issue with:
- **Feature Description**: What would you like to see?
- **Use Case**: Why would this be useful?
- **Implementation Ideas**: Any thoughts on how it could work?

### Submitting Code 🔧

1. **Fork the repository**
2. **Create a feature branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes**
4. **Test thoroughly**
5. **Commit with clear messages**:
   ```bash
   git commit -m "Add: New puzzle for Chapter 8"
   ```
6. **Push to your fork**:
   ```bash
   git push origin feature/your-feature-name
   ```
7. **Create a Pull Request**

---

## 📋 Development Guidelines

### Code Style
- Follow Java conventions (camelCase for methods, PascalCase for classes)
- Add comments for complex logic
- Keep methods focused and under 50 lines when possible
- Use meaningful variable names

### Project Structure
```
src/com/lordofthethesis/
├── Main.java              # Entry point
├── engine/                # Game logic
├── gui/                   # User interface
├── graphics/              # Rendering
├── audio/                 # Sound system
└── model/                 # Data models
```

### Adding New Chapters
To add a new story chapter:

1. Edit `GameEngine.java` → `createStoryChapters()`
2. Create puzzle text and correct answer
3. Add image to `assets/images/`
4. Add music to `assets/music/` (optional)
5. Update `updateRoomByChapter()` to map chapter to assets

### Adding New Music
1. Place WAV file in `assets/music/`
2. Update `GameEngine.java` music mapping
3. Keep files under 30 MB if possible
4. Use 8-bit/chiptune style to match theme

### Adding New Images
1. Create 800×480 PNG image
2. Use pixel art style (low-res, retro aesthetic)
3. Place in `assets/images/`
4. Reference in chapter's room key

---

## 🧪 Testing

Before submitting:
1. **Compile**: `./scripts/compile.sh`
2. **Run**: Test all commands
3. **Check**: No errors in console
4. **Verify**: Music and images load correctly

---

## 📖 Documentation

When adding features, update:
- `README.md` - User-facing documentation
- `docs/` - Technical documentation
- Code comments - Inline explanations

---

## 🎨 Asset Contributions

### Images
- Must be **800×480 PNG**
- Pixel art style
- LOTR-themed
- Original work or properly licensed

### Music
- Must be **WAV format**
- 8-bit/chiptune style
- LOTR-inspired
- Respect copyright - only remixes or original compositions

---

## 🌟 Recognition

All contributors will be:
- Listed in CONTRIBUTORS.md
- Credited in release notes
- Appreciated forever! 🙏

---

## ❓ Questions?

Open a **Discussion** on GitHub or create an issue with the `question` label.

---

## 📜 Code of Conduct

Be respectful, kind, and constructive. We're all here to have fun and create something cool inspired by Tolkien's masterpiece!

---

Thank you for contributing to this epic journey! 🧙‍♂️✨
