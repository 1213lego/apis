#!/usr/bin/env node

import inquirer from "inquirer";
import chalk from 'chalk';
import * as figlet from 'figlet';

const init = () => {
    console.log(
        chalk.green(
            figlet.textSync("Scaffolding", {
                font: "Standard",
                horizontalLayout: "default",
                verticalLayout: "default"
            })
        )
    );
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
    return inquirer.prompt(questions);
};
const run = async () => {
    // show script introduction
    init();
    // ask questions
    // ask questions
    const answers = await askQuestions();
    require('./generate-repository').cli(answers);
    // create the file
    // show success message
};

run();
//require('../src/cli').cli(process.argv);