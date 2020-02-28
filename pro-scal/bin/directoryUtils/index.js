"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const path_1 = __importDefault(require("path"));
const fs = __importStar(require("fs"));
const chalk_1 = __importDefault(require("chalk"));
const buildTree = (directory, node) => {
    fs.readdirSync(node.file).forEach(fileName => {
        if (fs.lstatSync(path_1.default.join(node.file, fileName)).isDirectory()) {
            node.files.push(buildTree(path_1.default.join(node.file, fileName), {
                file: path_1.default.join(node.file, fileName),
                parentFile: node,
                isDirectory: true,
                files: []
            }));
        }
        else {
            node.files.push({
                file: path_1.default.join(node.file, fileName),
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
        while (path_1.default.basename(directory) !== 'src') {
            directory = path_1.default.join(directory, "..");
        }
    }
    else {
        if (fs.readdirSync(directory).find((file) => file === 'src')) {
            directory = path_1.default.join(directory, 'src');
        }
        else {
            console.log(chalk_1.default.red('Couldnt continue. The current folder is not a java project'));
            process.exit(1);
        }
    }
    const filesDir = path_1.default.join(directory, "main", "java");
    fileTree.file = filesDir;
    buildTree(filesDir, fileTree);
    return fileTree;
};
const searchDirectory = (fileTree, folderName) => {
    if (fileTree.isDirectory && fileTree.file.includes(folderName))
        return fileTree;
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
//# sourceMappingURL=index.js.map