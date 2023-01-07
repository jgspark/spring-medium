### Run Command

![1.png](img%2F1.png)

* output is json

```
k6 run --vus 10 --duration 30s --out json=./k6/out.json ./k6/script.js
```

* output is csv

```
k6 run --vus 10 --duration 30s --out csv=./k6/out.csv ./k6/script.js
```

