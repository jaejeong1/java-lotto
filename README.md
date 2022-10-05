# 로또

## 1단계 - 문자열 계산기

## 기능 목록

사칙연산을 수행할 수 있는 계산기 구현

- 입력
  - 문자열을 입력한다. 이때, 입력 문자열의 숫자와 사칙 연산 사이에는 반드시 빈 공백이 있다.
    - 사칙연산 : `+`, `-`, `*`, `/`
    - 입력 예시 : `2 + 3 * 4 / 2`
  - 문자열이 null이거나 공백이라면 예외가 발생해야 한다.
  - 숫자가 들어올 위치에 숫자가 아닌 입력을 받으면 예외가 발생해야 한다.
  - `+`, `-`, `*`, `/` 이외의 사칙연산 기호를 받으면 예외가 발생해야 한다.
- 사칙연산 기능 구현
  - 덧셈
  - 뺄셈
  - 곱셈
  - 나눗셈 : 결과 값을 정수로 떨어지는 값으로 한정한다.
  - 사칙연산의 계산 순서는 입력 값에 따라 계산 순서가 결정된다. (예 : `2 + 3 * 4 / 2` = `10`)
