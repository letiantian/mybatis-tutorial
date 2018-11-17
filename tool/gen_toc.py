# coding: utf-8
import os
import sys
from urllib import quote

SUPPORT_FILETYPE_LIST = ('.ppt', '.pptx', '.doc', '.docx', '.eap', '.md')
DIR_PATH = os.path.dirname(os.path.realpath(__file__))
DIR_PATH = os.path.join(DIR_PATH, '..', 'docs')


base_dirs = []
for f in os.listdir(DIR_PATH):
    if f in ['img', 'imgs', 'image', 'images']:
        continue
    if not f.startswith('.') and os.path.isdir(os.path.join(DIR_PATH, f)):
        base_dirs.append(f)
base_dirs = sorted(base_dirs)

# print base_dirs

base_dirs_index = {}

FILE_NAME = 'FILE_NAME'
FILE_PATH = 'FILE_PATH'

for dir_path in base_dirs:
    base_dirs_index[dir_path] = []
    subdir_path = os.path.join(DIR_PATH, dir_path)
    for f in os.listdir(subdir_path):
        if f.startswith('.') or f in ['img', 'imgs', 'image', 'images']:
            continue
        base_dirs_index[dir_path].append({FILE_NAME: f, FILE_PATH: 'docs/' + quote(dir_path) + '/' + quote(f)})
        base_dirs_index[dir_path] = sorted(base_dirs_index[dir_path])

# print base_dirs_index


for dir_path in base_dirs:
    print '* [{}]({})'.format(dir_path, quote(dir_path))
    for item in base_dirs_index[dir_path]:
        print '\t* [{}]({})'.format(item[FILE_NAME], item[FILE_PATH])
