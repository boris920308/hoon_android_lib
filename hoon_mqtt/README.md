
## 빌드 준비
- main/res/raw 속 인증파일 세가지 추가
  - amazon_root_ca1.pem
  - certificate_pem_crt
  - private_pem_key
- local.properties에 AWS ENDPOINT 선언
  - 예시) AWS_IOT_ENDPOINT="example.iot.ap-southeast-2.amazonaws.com"