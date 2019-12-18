"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express = require("express");
const testController = require('./../controllers/testController');
const router = express.Router();
router.get('/test', testController.testController);
module.exports = router;
//# sourceMappingURL=index.js.map