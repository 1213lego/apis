import path from "path";
import * as fs from "fs";
import chalk from "chalk";

const buildTree = (directory: string, node: FileNode) => {
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

interface FileNode {
    file: string,
    parentFile: FileNode | null,
    isDirectory: boolean,
    files: Array<FileNode>
}

const directoryTree = (directory: string) => {
    const fileTree: FileNode = {
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
        if (fs.readdirSync(directory).find((file: string) => file === 'src')) {
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
const searchDirectory = (fileTree: FileNode, folderName: string): FileNode | null => {
    if (fileTree.isDirectory && fileTree.file.includes(folderName)) return fileTree;
    for (let i = 0; i < fileTree.files.length; i++) {
        const result = searchDirectory(fileTree.files[i], folderName);
        if (result) {
            return result;
        }
    }
    return null;
};
module.exports = {
    directoryTree,
    searchDirectory
};