const fs = require('fs');
const path = require('path');
const BASE_REPOSITORY_PACKAGE = 'import org.springframework.data.jpa.repository.';
const chalk = require("chalk");

function cli(args) {
    console.log(args);
    const repositoryPackage = "";
    const repositoryImport = `${BASE_REPOSITORY_PACKAGE}${args.repositoryInterface}`;
    const repositoryName = `${args.enityName}Repository`;
    const repositoryTemplate = `${repositoryPackage};
    ${repositoryImport};
    public interface ${repositoryName} extends ${args.repositoryInterface}<${args.enityName},${args.typeId}> {
    }`;
    const repositoryFile = fs.createWriteStream(`./${repositoryName}.java`);
    repositoryFile.write(repositoryTemplate, (error => console.log(error || '')));
    exploreFiles(process.cwd());
}

function buildTree(directory, node) {
    console.log(directory, node);
    fs.readdirSync(node.file).forEach(fileName => {
        if (fs.lstatSync(path.join(node.file, fileName)).isDirectory()) {
            node.files.push(buildTree(path.join(node.file, fileName), {
                file: path.join(node.file, fileName),
                parentFile: node,
                files: []
            }))
        }
    });
}

function exploreFiles(directory) {
    const fileTree = {
        file: '',
        parentFile: null,
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
}

module.exports = {
    cli: cli
};