const expressLoader = require('./express');
module.exports = ({ expressApp }) => {
    expressLoader({ app: expressApp });
    console.log('Express ok');
    //other loaders, db mongoose etc
};
//# sourceMappingURL=index.js.map