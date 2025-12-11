const express = require('express');
const app = express();
const port = 4200;

app.use(express.static('dist/periodic-table-app/browser'));

app.get('/config', (req, res) => {
  res.json({
    BACKEND_URL: process.env.BACKEND_URL,
  })
})

app.listen(port, () => {
  console.log(`Periodic table website listening on ${port} port`)
});
