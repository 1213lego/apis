const fs = require('fs');
const BASE_REPOSITORY_PACKAGE = 'import org.springframework.data.jpa.repository.';
const directoryUtils = require('./directoryUtils');
const path = require('path');

function getPackage(pathPackage: string) {
    let parts = pathPackage.split(path.sep);
    parts = parts.splice(parts.findIndex(value => value === "java") + 1);
    return parts.join(".");
}

function generateRepository(args: { repositoryInterface: any; enityName: any; entityPackage: any; repositoryPackage: any; typeId: any; }) {
    const repositoryImport = `${BASE_REPOSITORY_PACKAGE}${args.repositoryInterface}`;
    const repositoryName = `${args.enityName}Repository`;
    const directoryTree = directoryUtils.directoryTree(process.cwd());
    const entityPath = directoryUtils.searchDirectory(directoryTree, args.entityPackage);
    const repositoryPath = directoryUtils.searchDirectory(directoryTree, args.repositoryPackage);
    const repositoryTemplate = `package ${getPackage(repositoryPath.file)};
    import ${getPackage(entityPath.file)}.${args.enityName};
    ${repositoryImport};
    public interface ${repositoryName} extends ${args.repositoryInterface}<${args.enityName},${args.typeId}> {
    }`;
    console.log(path.join(repositoryPath.file, `${repositoryName}.java`));
    const repositoryFile = fs.createWriteStream(path.join(repositoryPath.file, `${repositoryName}.java`));
    repositoryFile.write(repositoryTemplate, ((error: any) => console.log(error || '')));
}


module.exports = {
    cli: generateRepository
};