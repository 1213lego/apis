const chalk = require("chalk");
const path = require('path');
const fs = require('fs');
const buildTree = (directory, node) => {
    fs.readdirSync(node.file).forEach(fileName => {
        if (fs.lstatSync(path.join(node.file, fileName)).isDirectory()) {
            node.files.push(buildTree(path.join(node.file, fileName), {
                file: path.join(node.file, fileName),
                parentFile: node,
                isDirectory: true,
                files: []
            }))
        } else {
            node.files.push({
                file: path.join(node.file, fileName),
                parentFile: node,
                isDirectory: false,
                files: []
            });
        }
    });
    return node;
};
const directoryTree = (directory) => {
    const fileTree = {
        file: '',
        parentFile: null,
        isDirectory: true,
        files: []
    };
    const isInSrc = directory.includes('src');
    if (isInSrc) {
        while (path.basename(directory) !== 'src') {
            directory = path.join(directory, "..");
        }
    } else {
        if (fs.readdirSync(directory).find(file => file === 'src')) {
            directory = path.join(directory, 'src');
        } else {
            console.log(chalk.red('Couldnt continue. The current folder is not a java project'));
            process.exit(1);
        }
    }
    const filesDir = path.join(directory, "main", "java");
    fileTree.file = filesDir;
    buildTree(filesDir, fileTree);
    return fileTree;
};
const searchDirectory = (fileTree, folderName) => {
    if (fileTree.isDirectory && fileTree.file.includes(folderName)) return fileTree;
    for (let i = 0; i < fileTree.files.length; i++) {
        const result = searchDirectory(fileTree.files[i], folderName);
        if (result) {
            return result;
        }
    }
    return null;
}
module.exports = {
    directoryTree,
    searchDirectory
}