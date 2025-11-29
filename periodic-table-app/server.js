const express = require('express');
const app = express();
const port = 4200;

app.use(express.static('dist/periodic-table-app/browser'));

app.listen(port, () => {
  console.log(`Periodic table app listening on ${port} port`);
});
