# Moodrop 
- 사용자의 감정 및 보유 원료에 따른 향수 추천
- 보유 원료를 알고리즘에 의해 추천해주고, 추천한 원료의 비중을 조절해서 스스로만의 레시피 만들기.

[서비스 사진]

## DB
1. DB ERD 작성
2. Top, Middle, Base 분류에 따른 중복 데이터 제거
<img width="1450" height="1282" alt="image" src="https://github.com/user-attachments/assets/f582d7b6-8b80-49d3-a91b-f906b719c0ee" />

## 백엔드 코드 작성
1. 향수 데이터 웹 스크래핑(Selenium) 및 DB에 적재
2. 사용자가 선택한 감정 및 보유 원료에 따른 향수 추천
3. 추천한 향수에서 여러 Note(원료)에서 자신이 선택한 느낌을 내는 데 가장 주요하게 작용하는 Note를 추출
4. 선택한 Note(원료)로 비중을 조절하며 자기 자신이 만든 향수(레시피)를 저장
5. 자신이 만든 향수(레시피)에 대한 별점 평가

## CI/CD
1. Jenkins, Docker를 이용한 자동 배포
