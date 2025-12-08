import os
from pathlib import Path

def delete_class_files(root_dir="."):
    """
    Удаляет все .class файлы в директории и её поддиректориях.

    Args:
        root_dir: корневая директория для поиска (по умолчанию текущая директория)
    """
    deleted_count = 0
    root_path = Path(root_dir)

    # Ищем все .class файлы рекурсивно
    for class_file in root_path.rglob("*.class"):
        try:
            class_file.unlink()  # Удаляем файл
            print(f"Удалён: {class_file}")
            deleted_count += 1
        except Exception as e:
            print(f"Ошибка при удалении {class_file}: {e}")

    print(f"\nВсего удалено файлов: {deleted_count}")

if __name__ == "__main__":
    delete_class_files()