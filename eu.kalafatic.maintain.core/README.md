eu.kalafatic.maintain.core

What I changed
- Added Maven dependencies for Eclipse Draw2D and Zest to the module pom.xml so the Java imports in `ZestLabelProvider.java` can be resolved by a normal Maven build.

Why the original error occurred
- The compiler errors you posted complain that packages `org.eclipse.draw2d` and `org.eclipse.zest.core.viewers` are missing. Those packages are provided by Eclipse plugins (Draw2D / Zest). A plain javac/maven-compiler invocation can't see PDE plugin runtime libraries unless you either:
  * add corresponding JAR dependencies to the Maven build (what I did in the pom), or
  * build with Tycho / use an Eclipse target platform / build inside Eclipse PDE (recommended for Eclipse plugin projects).

How to build locally (Windows cmd.exe)
1) Option A — using Maven (requires Apache Maven installed and on PATH):

   cd /d C:\Users\petrk\git\repository\maintain\eu.kalafatic.maintain.core
   mvn -DskipTests=true package

2) Option B — build inside Eclipse PDE (recommended for plugin projects):
   - Import the project as an existing Eclipse project and build/run from PDE. The PDE target platform will supply Draw2D/Zest at runtime.

3) Option C — Use Tycho for an OSGi/Tycho build (recommended for CI/eclipse-plugin packaging). If you want, I can convert/add a Tycho parent pom and sample configuration.

Check available Draw2D/Zest artifact coordinates (script)

I added a small helper script `tools\check-eclipse-deps.cmd` that tries several candidate Maven coordinates using `mvn dependency:get`. Run it from a Windows cmd.exe where Maven is installed and on PATH:

```
cd /d C:\Users\petrk\git\repository\maintain\eu.kalafatic.maintain.core\tools
check-eclipse-deps.cmd
```

Tycho build (recommended for Eclipse plugin projects)

I prepared `pom-tycho.xml` and a basic target platform definition in `target-platform\target-definition.target` that reference an Eclipse p2 update site (adjust the release URL to match your Eclipse target if needed). To run a Tycho build once you have Maven:

```
cd /d C:\Users\petrk\git\repository\maintain\eu.kalafatic.maintain.core
mvn -DskipTests=true -f pom-tycho.xml package
```

Troubleshooting
- If after installing Maven you still get missing-artifact errors for Draw2D or Zest, the artifact coordinates or versions in the pom might not be present in Maven Central. In that case the recommended approach is:
  - Use Tycho and a proper Eclipse target platform (the Tycho build resolves Eclipse plugin dependencies from an Eclipse p2 repository), or
  - Manually place the required plugin JARs into the project's `lib/` folder and add them as system-scoped dependencies in the pom.

Next steps I can take for you (pick one):
- Convert this project to a Tycho build (I can add a minimal parent pom and sample build configuration).
- Validate and adjust Draw2D/Zest artifact coordinates and versions to ones available on your environment (requires network access or Maven available to check). I can try different versions if you want.
- Add the Draw2D/Zest JARs into `lib/` and wire them into the build if you can provide the JARs or allow me to fetch them.

Status summary
- Edit: Updated `pom.xml` to add Draw2D and Zest dependencies — Done
- Local compile: Attempted but failed because Maven is not installed on the machine where I ran the check (`'mvn' is not recognized`).

If you want, I can proceed to add a Tycho pom now — say the word and I'll implement it and run local checks (I won't be able to run Tycho here either without Maven, but I will prepare everything so you can run it immediately after installing Maven).