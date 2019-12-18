import * as express from 'express';
import {join} from "path";
const cors = require('cors');
const helmet = require('helmet');
const routes = require('./../routes/');
module.exports = ({ app }: { app: express.Application }) => {
    app.use(cors());
    app.use(express.static(join(__dirname,'../','public')));
    app.use(helmet());
    app.use(express.json());
    app.use('/',routes);
    return app;
}

