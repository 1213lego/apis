import * as express from 'express';
const testController  = require('./../controllers/testController');
const router = express.Router();
router.get('/test',testController.testController);

module.exports = router;
