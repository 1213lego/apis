const loaders = require('./loaders/index');
const express = require('express');
const config = require('./config');
const app = express();
loaders({ expressApp: app });
app.listen(config.port, err => {
    if (err) {
        console.log(err);
        return;
    }
    console.log(`Server ok, listening at port ${config.port}`);
});
//# sourceMappingURL=app.js.map