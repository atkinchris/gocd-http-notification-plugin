const express = require('express')
const app = express()

app.all('/', function (req, res) {
  console.log(req.body)
  res.send('Hello World!')
})

app.listen(3000, function () {
  console.log('Example app listening on port 3000!')
})
