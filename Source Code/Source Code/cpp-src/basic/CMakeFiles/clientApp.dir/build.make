# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.28

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/homebrew/Cellar/cmake/3.28.3/bin/cmake

# The command to remove a file.
RM = /opt/homebrew/Cellar/cmake/3.28.3/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/src"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic"

# Include any dependencies generated for this target.
include CMakeFiles/clientApp.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/clientApp.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/clientApp.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/clientApp.dir/flags.make

CMakeFiles/clientApp.dir/apps/clientApp.cpp.o: CMakeFiles/clientApp.dir/flags.make
CMakeFiles/clientApp.dir/apps/clientApp.cpp.o: /Users/pushpalpatil/Desktop/M.S./Spring24/CMPE\ 275/socket-3code/cpp-src/basic/src/apps/clientApp.cpp
CMakeFiles/clientApp.dir/apps/clientApp.cpp.o: CMakeFiles/clientApp.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir="/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/clientApp.dir/apps/clientApp.cpp.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/clientApp.dir/apps/clientApp.cpp.o -MF CMakeFiles/clientApp.dir/apps/clientApp.cpp.o.d -o CMakeFiles/clientApp.dir/apps/clientApp.cpp.o -c "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/src/apps/clientApp.cpp"

CMakeFiles/clientApp.dir/apps/clientApp.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/clientApp.dir/apps/clientApp.cpp.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/src/apps/clientApp.cpp" > CMakeFiles/clientApp.dir/apps/clientApp.cpp.i

CMakeFiles/clientApp.dir/apps/clientApp.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/clientApp.dir/apps/clientApp.cpp.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/src/apps/clientApp.cpp" -o CMakeFiles/clientApp.dir/apps/clientApp.cpp.s

# Object files for target clientApp
clientApp_OBJECTS = \
"CMakeFiles/clientApp.dir/apps/clientApp.cpp.o"

# External object files for target clientApp
clientApp_EXTERNAL_OBJECTS =

clientApp: CMakeFiles/clientApp.dir/apps/clientApp.cpp.o
clientApp: CMakeFiles/clientApp.dir/build.make
clientApp: libbasic_socket.a
clientApp: CMakeFiles/clientApp.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --bold --progress-dir="/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/CMakeFiles" --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable clientApp"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/clientApp.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/clientApp.dir/build: clientApp
.PHONY : CMakeFiles/clientApp.dir/build

CMakeFiles/clientApp.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/clientApp.dir/cmake_clean.cmake
.PHONY : CMakeFiles/clientApp.dir/clean

CMakeFiles/clientApp.dir/depend:
	cd "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/src" "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/src" "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic" "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic" "/Users/pushpalpatil/Desktop/M.S./Spring24/CMPE 275/socket-3code/cpp-src/basic/CMakeFiles/clientApp.dir/DependInfo.cmake" "--color=$(COLOR)"
.PHONY : CMakeFiles/clientApp.dir/depend

