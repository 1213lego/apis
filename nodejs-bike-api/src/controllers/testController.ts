import {NextFunction, Response, Request} from "express";
const testController = (req: Response, res: Response) => {
    res.status(200);
    res.send(require('./../services/TestService').getSome());
};

module.exports = {
    testController
}
