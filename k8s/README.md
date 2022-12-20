# Distributed Processing Assignment

### 1. 적절한 위치로 이동
```shell
cd /vagrant/project
```

### 2. Minikube 시작

```shell
minikube start --mount --mount-string $(pwd)/data:/log --driver=docker --memory max
```

### 3. Ingress 활성화

```shell
minikube addons enable ingress
```

### 4. git clone
```shell
git clone https://github.com/govl6113/distributed_processing.git
```

### 5. move
```shell
cd k8s
```

### 6. Kubernetes 상태 적용
```shell
kubectl apply -k .
```

### 7. Ingress url 알아보기

```shell
minikube service ingress-nginx-controller -n ingress-nginx --url
# http://192.168.49.2:55593 <-- http  port
# http://192.168.49.2:55594 <-- https port
```