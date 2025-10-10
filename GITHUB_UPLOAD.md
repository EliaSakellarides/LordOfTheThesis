# 📤 GitHub Upload Guide

## Steps to Upload to GitHub

### 1. Create a New Repository on GitHub

1. Go to [github.com](https://github.com) and log in
2. Click the **+** button in the top-right corner
3. Select **"New repository"**
4. Fill in the details:
   - **Repository name**: `lord-of-the-thesis`
   - **Description**: "An epic Lord of the Rings themed text adventure with pixel art and 8-bit music"
   - **Visibility**: Choose **Public** or **Private**
   - **DO NOT** initialize with README, .gitignore, or license (we already have these)
5. Click **"Create repository"**

### 2. Connect Your Local Repository

After creating the repository, GitHub will show you commands. Use these:

```bash
# Navigate to your project
cd /tmp/LordOfTheThesis

# Add GitHub as remote
git remote add origin https://github.com/YOUR_USERNAME/lord-of-the-thesis.git

# Rename branch to main (if needed)
git branch -M main

# Push to GitHub
git push -u origin main
```

**Replace `YOUR_USERNAME`** with your actual GitHub username!

### 3. Verify Upload

1. Refresh your GitHub repository page
2. You should see all files uploaded
3. The README.md will display automatically

---

## Alternative: Using GitHub CLI

If you have GitHub CLI installed (`gh`):

```bash
# Create repository directly from terminal
gh repo create lord-of-the-thesis --public --source=. --remote=origin

# Push code
git push -u origin main
```

---

## What Was Committed

✅ **Source Code** (src/)
✅ **Compiled Classes** - EXCLUDED by .gitignore
✅ **Assets** (images + music)
✅ **Build Scripts**
✅ **Documentation**
✅ **README.md** - Professional, GitHub-ready

---

## Important Notes

### Large Files
Your repository includes:
- 8 large music files (13-30 MB each, ~126 MB total)
- 8 pixel art images (1.8-2.0 MB each, ~15 MB total)

**Total repo size: ~141 MB**

GitHub allows up to **100 MB per file** and **recommended max 1 GB per repo**, so you're fine!

### If You Get "File Too Large" Error

If GitHub complains about file sizes, you can use **Git LFS** (Large File Storage):

```bash
# Install Git LFS
brew install git-lfs  # macOS
# or download from: https://git-lfs.github.com

# Initialize LFS
git lfs install

# Track large files
git lfs track "*.wav"
git lfs track "*.png"

# Add .gitattributes
git add .gitattributes

# Commit and push
git commit -m "Add Git LFS tracking for audio and images"
git push
```

---

## After Upload

### Update README Links

In `README.md`, replace:
```markdown
[Download](https://github.com/yourusername/lord-of-the-thesis/releases)
```

With your actual GitHub username:
```markdown
[Download](https://github.com/YOUR_USERNAME/lord-of-the-thesis/releases)
```

### Add Topics/Tags on GitHub

On your repository page, click **"Add topics"** and add:
- `java`
- `game`
- `text-adventure`
- `lord-of-the-rings`
- `lotr`
- `pixel-art`
- `8-bit`
- `chiptune`
- `adventure-game`

This helps people discover your project!

---

## Creating a Release

To create a downloadable release:

1. On GitHub, go to **Releases** → **"Create a new release"**
2. Tag: `v1.0.0`
3. Title: `Lord of the Thesis v1.0 - Initial Release`
4. Description:
   ```
   🌋 First public release of Lord of the Thesis!
   
   Features:
   - 10 epic chapters through Middle-earth
   - Custom pixel art visuals
   - 8-bit LOTR soundtrack
   - Cinematic intro sequence
   
   Download, compile, and play!
   ```
5. Attach compiled JAR (optional)
6. Click **"Publish release"**

---

## Troubleshooting

### Authentication Issues

GitHub may ask for credentials. Use:
- **Personal Access Token** (recommended)
- Or configure SSH keys

Generate token at: https://github.com/settings/tokens

### Branch Name

If GitHub expects `main` but you have `master`:
```bash
git branch -M main
git push -u origin main
```

---

## Done! 🎉

Your project is now on GitHub and looks professional!

Share the link:
```
https://github.com/YOUR_USERNAME/lord-of-the-thesis
```
