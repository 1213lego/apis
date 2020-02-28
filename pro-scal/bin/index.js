#!/usr/bin/env node
"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
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
const inquirer_1 = __importDefault(require("inquirer"));
const chalk_1 = __importDefault(require("chalk"));
const figlet = __importStar(require("figlet"));
const init = () => {
    console.log(chalk_1.default.green(figlet.textSync("Scaffolding", {
        font: "Standard",
        horizontalLayout: "default",
        verticalLayout: "default"
    })));
};
const askQuestions = () => {
    const questions = [
        {
            name: "enityName",
            type: "input",
            message: "What is the name of the Entity?"
        },
        {
            name: "entityPackage",
            type: "input",
            message: "What is the Entity Package?",
            default: "domain"
        },
        {
            name: "typeId",
            type: "input",
            message: "What is the type of the primary key?",
            default: 'Long'
        },
        {
            type: "list",
            name: "repositoryInterface",
            message: "What is the repository?",
            choices: ["JpaRepository", "MongoRepository", "CrudRepository", "PagingAndSortingRepository", "Repository"]
        },
        {
            name: "repositoryPackage",
            type: "input",
            message: "What is the Repository package?",
            default: "repository"
        }
    ];
    return inquirer_1.default.prompt(questions);
};
const run = () => __awaiter(void 0, void 0, void 0, function* () {
    // show script introduction
    init();
    // ask questions
    // ask questions
    const answers = yield askQuestions();
    require('./generate-repository').cli(answers);
    // create the file
    // show success message
});
run();
//require('../src/cli').cli(process.argv);
//# sourceMappingURL=index.js.map