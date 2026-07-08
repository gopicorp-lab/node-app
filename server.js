const express = require("express");

const app = express();

let memory = [];

app.get("/", (req, res) => {
  // allocate ~10MB each request
  memory.push(Buffer.alloc(10 * 10 * 10));

  res.send("Memory allocation test");
});

app.listen(3000, () => {
  console.log("Server running on port 3000");
});