# How to contribute to Hacky Pi
First of all many thanks for your interest in contributing to Hacky Pi. We need you in order to keep this training up-to-date with current security challenges in embedded systems projects. :+1::tada:


## Reporting Bugs
If you discover undesired behavior, report the bug that contributors can fix it. But first make sure that you cannot find a related issue on GitHub. If a similar issue is still open, rather comment on that instead of creating a new one. If you find a closed issue that matches, mention it in your report. Otherwise, we encourage you to open a totally new issue with the bug label. Provide a meaningful title to identify the problem. Also, describe the exact steps with explanations for reproduction. What is the behavior you observed and what behavior was expected? Round off the report with screenshots if necessary. The community will then take care of this bug.


## Suggesting Enhancements
Similarly to reporting bugs, you can also suggest enhancements to the repository. An enhancement can be a proposal for a new challenge or an improvement to the existing codebase. Follow the same process as before but mark the issue with the enhancement label.


## Branching / Pull Request Concept
Branch names should be meaningful and user-friendly to read. Therefore, we suggest following schema: *\<prefix\>-\<#issue\>-\<description\>*

- Prefix is either *bugfix* or *feature* for enhancements and new challenges.
- Issue number is available on GitHub.
- Description should be short and use hyphens as delimiter if multiple words are required.

After the work on the branch is done and pushed, a pull request needs to be created. This enables the community to discuss on the code and the maintainer to approve and merge into the main branch if everything is okay.


## Creating Challenges
We encourage training participants and other community members to create new challenges based on enhancement issues or own ideas. This helps internalizing security problems and also learning some Yocto.

1. Pick a random challenge title that does not give a hint for the solution. Usually, we choose an adjective and a personal name, e.g. *mad-margrethe*.
1. Create a folder for your challenge in the custom Yocto layer like *meta-hackypi/recipes-vulnerable/\<challenge-name\>*.
    1. Add a BitBake recipe (*\<challenge-name\>.bb*) desribing the opkg package to be installed for the challenge. Base yourself on the existing challenge recipes in order to make it right.
    1. Put additional files like initialization scripts in a separate subfolder that can be referenced by the recipe.
1. If the challenge includes some code to be compiled for the handout, create a folder under *src/*.
    1. Place your source code in that folder together with a compilation script or a Makefile. It should be straightforward to compile the code for the handout.
1. Look at the GitHub Actions workflow and add your challenge recipe similarly to other challenges to the tasks.
1. That's it for the current repository. Now, some documentation is needed in the [handout repository](https://github.com/nimarty/hackypi-handout). Add the compiled binaries as well, if you have some and mention how to use them. Finally, link the challenge description in the top readme file, so it is discoverable for trainees.

**Keep in mind to create an own feature branch for the new challenge. Test it by yourself or let it test by others. If you think, it is ready to merge, finally create a pull request to be reviewed.**
