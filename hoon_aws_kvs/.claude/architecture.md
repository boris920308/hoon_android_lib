# Android Clean Architecture

## 패키지 구조

```
hoon.example.hoonawskvs/
├── presentation/          # UI 레이어 (Presentation Layer)
│   ├── ui/
│   │   ├── screen/        # Composable Screen들
│   │   ├── component/     # 재사용 가능한 UI 컴포넌트
│   │   └── theme/         # 테마 (Color, Type, Theme)
│   └── viewmodel/         # ViewModel들
│
├── domain/                # 도메인 레이어 (Domain Layer)
│   ├── model/             # Entity/Domain Model
│   ├── repository/        # Repository Interface
│   └── usecase/           # UseCase (비즈니스 로직)
│
├── data/                  # 데이터 레이어 (Data Layer)
│   ├── repository/        # Repository Implementation
│   ├── datasource/
│   │   ├── local/         # Local DataSource (Room, SharedPrefs 등)
│   │   └── remote/        # Remote DataSource (API, WebSocket 등)
│   ├── mapper/            # DTO <-> Domain Model 변환
│   └── dto/               # Data Transfer Object
│
└── di/                    # Dependency Injection (Hilt/Koin)
```

## 레이어별 역할

### Presentation Layer
- UI 표시 담당
- ViewModel에서 상태 관리
- Domain Layer에만 의존

### Domain Layer
- 비즈니스 로직 담당
- 순수 Kotlin (Android 의존성 없음)
- 다른 레이어에 의존하지 않음

### Data Layer
- 데이터 소스 관리
- Domain Layer의 Repository Interface 구현
- Domain Layer에만 의존

## 의존성 방향

```
Presentation → Domain ← Data
```

## 참고사항
- AWS KVS WebRTC 프로젝트
- Jetpack Compose 사용
- Master/Viewer 기능 구현 예정
