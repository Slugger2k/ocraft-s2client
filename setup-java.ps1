# PowerShell-Skript zum Einrichten von Java im Cursor-Terminal
# Dieses Skript hilft beim Finden und Konfigurieren von Java

Write-Host "=== Java Setup für Cursor Terminal ===" -ForegroundColor Cyan
Write-Host ""

# Prüfe, ob Java bereits installiert ist
Write-Host "1. Prüfe auf vorhandene Java-Installationen..." -ForegroundColor Yellow

$javaPaths = @(
    "C:\Program Files\Java",
    "C:\Program Files (x86)\Java",
    "$env:LOCALAPPDATA\Programs\Eclipse Adoptium",
    "$env:ProgramFiles\Eclipse Adoptium",
    "$env:ProgramFiles\Microsoft",
    "$env:LOCALAPPDATA\Microsoft\WindowsApps"
)

$foundJava = $false

foreach ($path in $javaPaths) {
    if (Test-Path $path) {
        Write-Host "   Gefunden: $path" -ForegroundColor Green
        $jdkDirs = Get-ChildItem -Path $path -Directory -ErrorAction SilentlyContinue | Where-Object { $_.Name -like "*jdk*" -or $_.Name -like "*java*" }
        foreach ($jdkDir in $jdkDirs) {
            $javaExe = Join-Path $jdkDir.FullName "bin\java.exe"
            if (Test-Path $javaExe) {
                Write-Host "   -> JDK gefunden: $($jdkDir.FullName)" -ForegroundColor Green
                $foundJava = $true
            }
        }
    }
}

# Prüfe auch in PATH
$pathDirs = $env:PATH -split ';'
foreach ($dir in $pathDirs) {
    if ($dir -and $dir.Trim()) {
        $javaExe = Join-Path $dir "java.exe"
        if (Test-Path $javaExe) {
            Write-Host "   -> Java im PATH gefunden: $dir" -ForegroundColor Green
            $foundJava = $true
        }
    }
}

if (-not $foundJava) {
    Write-Host "   Keine Java-Installation gefunden!" -ForegroundColor Red
    Write-Host ""
    Write-Host "=== Installationsanleitung ===" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Dieses Projekt benötigt Java 25 (siehe pom.xml)" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Option 1: Eclipse Temurin (Empfohlen)" -ForegroundColor Green
    Write-Host "   1. Besuchen Sie: https://adoptium.net/temurin/releases/" -ForegroundColor White
    Write-Host "   2. Laden Sie JDK 25 für Windows herunter" -ForegroundColor White
    Write-Host "   3. Installieren Sie das JDK" -ForegroundColor White
    Write-Host "   4. Führen Sie dieses Skript erneut aus" -ForegroundColor White
    Write-Host ""
    Write-Host "Option 2: Mit winget (Windows Package Manager)" -ForegroundColor Green
    Write-Host "   winget install EclipseAdoptium.Temurin.25.JDK" -ForegroundColor White
    Write-Host ""
    Write-Host "Option 3: Mit Chocolatey (falls installiert)" -ForegroundColor Green
    Write-Host "   choco install temurin25jdk" -ForegroundColor White
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "2. Konfiguriere Umgebungsvariablen..." -ForegroundColor Yellow
    
    # Versuche JAVA_HOME zu finden
    $javaHome = $env:JAVA_HOME
    if (-not $javaHome) {
        # Suche nach java.exe und leite JAVA_HOME ab
        $javaExe = Get-Command java -ErrorAction SilentlyContinue
        if ($javaExe) {
            $javaHome = Split-Path (Split-Path $javaExe.Source)
            Write-Host "   JAVA_HOME wird auf gesetzt: $javaHome" -ForegroundColor Green
        }
    } else {
        Write-Host "   JAVA_HOME ist bereits gesetzt: $javaHome" -ForegroundColor Green
    }
    
    Write-Host ""
    Write-Host "=== Nächste Schritte ===" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Um Java dauerhaft einzurichten:" -ForegroundColor Yellow
    Write-Host "1. Öffnen Sie die Systemumgebungsvariablen:" -ForegroundColor White
    Write-Host "   - Windows-Taste + R" -ForegroundColor Gray
    Write-Host "   - sysdm.cpl eingeben" -ForegroundColor Gray
    Write-Host "   - Registerkarte 'Erweitert' -> 'Umgebungsvariablen'" -ForegroundColor Gray
    Write-Host ""
    Write-Host "2. Fügen Sie hinzu:" -ForegroundColor White
    Write-Host "   JAVA_HOME = [Pfad zu Ihrem JDK]" -ForegroundColor Gray
    Write-Host "   PATH += %JAVA_HOME%\bin" -ForegroundColor Gray
    Write-Host ""
    Write-Host "3. Starten Sie Cursor neu" -ForegroundColor White
    Write-Host ""
    Write-Host "Oder für diese Session temporär:" -ForegroundColor Yellow
    if ($javaHome) {
        Write-Host "   `$env:JAVA_HOME = '$javaHome'" -ForegroundColor Cyan
        Write-Host "   `$env:PATH = '$javaHome\bin;' + `$env:PATH" -ForegroundColor Cyan
    }
}

Write-Host ""
Write-Host "=== Test ===" -ForegroundColor Cyan
Write-Host "Nach der Installation testen Sie mit:" -ForegroundColor Yellow
Write-Host "   java -version" -ForegroundColor White
Write-Host "   javac -version" -ForegroundColor White
Write-Host ""
