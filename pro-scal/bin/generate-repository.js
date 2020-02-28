"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const fs_1 = __importDefault(require("fs"));
const path_1 = __importDefault(require("path"));
const BASE_REPOSITORY_PACKAGE = 'import org.springframework.data.jpa.repository.';
const directoryUtils = require('./directoryUtils');
function getPackage(pathPackage) {
    let parts = pathPackage.split(path_1.default.sep);
    parts = parts.splice(parts.findIndex(value => value === "java") + 1);
    return parts.join(".");
}
function generateRepository(args) {
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
    console.log(path_1.default.join(repositoryPath.file, `${repositoryName}.java`));
    const repositoryFile = fs_1.default.createWriteStream(path_1.default.join(repositoryPath.file, `${repositoryName}.java`));
    repositoryFile.write(repositoryTemplate, ((error) => console.log(error || '')));
}
module.exports = {
    cli: generateRepository
};
//# sourceMappingURL=generate-repository.js.map