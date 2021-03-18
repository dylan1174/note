import csv
import json

# 输入为oneforall结果文件名
# 输出为状态码为200时共有多少子域名 以及输出的文件路径

fieldname = ("id","alive","request","resolve","url","subdomain","level","cname","ip","public","cdn","port","status","reason","title","banner","cidr","asn","org","addr","isp","source")

# host = input("请输入要整理的csv域名:")
# file_path = host + '.csv'

host = 'gf.com.cn'
file_path = host + '.csv'
print("开始整理" + file_path)
result_file_path = host + 'result.txt'

# 将csv读取的字典存入sub_res列表中
sub_res = []
with open(file_path,'r') as csv_file:
    reader = csv.DictReader(csv_file,fieldname)

    for row in reader:
        sub_res.append(row)

# 获得所有返回状态码为200的子域名 加入subdomains
subdomains = []
for i in range(1,len(sub_res)):
    if sub_res[i]['status'] == '200':
        subdomains.append(sub_res[i]['subdomain'] + '\n')


print('共获得子域名' +str(len(subdomains)) + '个')

with open(result_file_path,'w') as f:
    f.writelines(subdomains)

print('储存文件地址:' + result_file_path)


# print()
# 不让中文显示为ascii
# out = json.dumps([row for row in reader],ensure_ascii=False)

# json_file.write(out)

