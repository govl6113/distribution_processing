
## Introduction to Distributed Systems
### Concept : "백종원의 가게들"<br>
각 서비스는 다음을 나타냅니다. [[API 명세](https://brash-wanderer-5cd.notion.site/280be039863f42c9a3e866b82ab77cab?v=ae38784a1bd942188dc84ebf9999fe2e)]
- 빽다방 (메뉴에 관련된 API)
- 미정국수 (주문에 관련된 API)
- 홍콩반점 (쿠폰에 관련된 API)

분산 시스템이 잘 동작하는지 확인하기 위해 각 replica 별로 로깅을 남겼고, 모든 로깅은 Redis의 Pub/Sub을 통해 logging system에 모아집니다. 
<br>
<br>

## How each component is implemented
<img
  src="https://user-images.githubusercontent.com/81179951/211201622-184dffdf-0702-4f85-9526-c6394fbbad7e.png"
  width="550"
  height="600"
/>
<br>
<br>
<br>

## How component are integrated
<img
  src="https://user-images.githubusercontent.com/81179951/211201666-9a3a60f9-3c5d-4589-86b2-de420a374a0d.png"
  width="900"
  height="500"
/>
## Workflow
<img
  src="https://user-images.githubusercontent.com/81179951/208570269-d7b73894-f422-4198-bb27-d536815c352c.png"
  width="900"
  height="400"
/>  
왼쪽에 존재하는 Pod 6개는 사용자의 요청을 받아 각 Replica의 로그를 남기는 동작을 수행하며, 동시에 Redis의 Service로 일정 data를 Publish.<br>
Redis에 보내진 data는 오른쪽에 존재하는 logging이라는 Pod 속 애플리케이션에서 Subscribe.
<br>
<br>
<br>

## Guides
### 1. Minikube 시작

```shell
minikube start --mount --mount-string $(pwd)/data:/log --driver=docker --memory max
```

### 2. Ingress 활성화

```shell
minikube addons enable ingress
```

### 3. Kubernetes 상태 적용
```shell
kubectl apply -k .
```

### 4. Ingress url 알아보기

```shell
minikube service ingress-nginx-controller -n ingress-nginx --url
# http://192.168.49.2:55593 <-- http  port
# http://192.168.49.2:55594 <-- https port
```
<br>
<br>

## Evaluation
### Client check

1. ingress url 확인 
![image](https://user-images.githubusercontent.com/81179951/211200910-c8269f95-c491-421b-805c-d63689d488a2.png)

2. 윗 빨간 박스: request, 아래 빨간 박스: response
![image](https://user-images.githubusercontent.com/81179951/211201147-cfdaafe4-a53c-490e-9136-5afb6bdc5bbb.png)

### Replica check
![image](https://user-images.githubusercontent.com/81179951/211201273-c9c576f7-0673-410b-808d-61881c6731b3.png)

### Subscribe logging system check
![image](https://user-images.githubusercontent.com/81179951/211201347-0a0be73e-14fb-463c-adbf-31fef25c2315.png)
