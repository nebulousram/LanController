git remote -v
git remote set-url origin https://github.com/nebulousram/LanController.git
git pull origin master --allow-unrelated-histories
git init
git remote add origin https://github.com/nebulousram/LanController.git
git add .
git commit -m "Update project with latest working changes"
git push -u origin master
git push -u origin master --force
pause