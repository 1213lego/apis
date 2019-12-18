"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express = require("express");
const path_1 = require("path");
const helmet = require('helmet');
const routes = require('./../routes/');
module.exports = ({ app }) => {
    app.use(express.static(path_1.join(__dirname, '../', 'public')));
    app.use(helmet());
    app.use(express.json());
    app.use('/', routes);
    return app;
};
//# sourceMappingURL=express.js.map