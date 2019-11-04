package com.zvz.serviceblog.service.impl;

import com.zvz.serviceblog.common.CommonConstant;
import com.zvz.serviceblog.common.PageConfig;
import com.zvz.serviceblog.common.PageInfoResult;
import com.zvz.serviceblog.mapper.IArticleDao;
import com.zvz.serviceblog.mapper.ICategoryDao;
import com.zvz.serviceblog.mapper.IMessageDao;
import com.zvz.serviceblog.mapper.ITagDao;
import com.zvz.serviceblog.model.Article;
import com.zvz.serviceblog.model.Category;
import com.zvz.serviceblog.model.Tag;
import com.zvz.serviceblog.service.IBArticleService;
import com.zvz.serviceblog.util.ReturnUtil;
import com.zvz.serviceblog.vo.ArchiveVo;
import com.zvz.serviceblog.vo.ArticleVo;
import com.zvz.serviceblog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service("articleService")
public class BArticleServiceImpl implements IBArticleService {
    @Autowired
    private ICategoryDao iCategoryDao;
    @Autowired
    private ITagDao iTagDao;
    @Autowired
    private IArticleDao iArticleDao;
    @Autowired
    private IMessageDao iMessageDao;

    @Override
    public Integer add(ArticleVo article) {
        if (article.getStatus() == CommonConstant.ACTICLE_STATUS_BLOG) {
            article.setPubTime(new Date());
        }
        article.setCode(new SimpleDateFormat("SSSMMssddmmHHyy").format(new Date()));
        article.setTagIds(getTagIdsBy(article.getTagNames()));
        article.setWriteTime(new Date());
        Integer result = iArticleDao.insert(article);
        if (ReturnUtil.returnResult(result)) {
            return article.getId();
        } else {
            return 0;
        }

    }

    @Override
    public Integer edit(ArticleVo article) {
        Integer result = 0;
        if (article.getOnlyChangeStatus() != null
                && article.getOnlyChangeStatus() == true) {
            result = iArticleDao.updateStatus(article);
        } else {
            if (article.getStatus() == CommonConstant.ACTICLE_STATUS_BLOG) {
                article.setPubTime(new Date());
            }
            article.setTagIds(getTagIdsBy(article.getTagNames()));
            result = iArticleDao.update(article);
        }
        if (ReturnUtil.returnResult(result)) {
            return article.getId();
        } else {
            return 0;
        }
    }

    @Override
    public Boolean deleteById(int articleId) {
        Article article = iArticleDao.selectById(articleId);
        if (article != null) {
            // 获取该文章id对应的所有评论记录总数
            Integer counts = iMessageDao.selectCountByArticleId(articleId);
            // 返回删除所有评论的文章为id的受影响行数
            Integer results = iMessageDao.deleteByArticleId(articleId);
            if (results == counts) {
                Integer result = iArticleDao.delete(articleId);
                return ReturnUtil.returnResult(result);
            }
        }
        return false;
    }

    @Override
    public Boolean deleteTag(int tagId, int articleId) {
        Article article = iArticleDao.selectById(articleId);
        if (article != null) {
            String tagIdsStr = article.getTagIds();
            String str = tagIdsStr.replaceAll((tagId + ","), "");
            article.setTagIds(str);
            Integer result = iArticleDao.update(article);
            return ReturnUtil.returnResult(result);
        }
        return false;
    }

    @Override
    public Article getById(int articleId) {
        Article article = iArticleDao.selectById(articleId);
        updateAritcleHits(articleId);
        return article;
    }

    @Override
    public ArticleVo getVoById(int articleId) {
        ArticleVo article = iArticleDao.selectVoById(articleId);
        if (article == null) {
            return null;
        }
        updateAritcleHits(articleId);
        article.setTagNames(getAllTagNamesBy(article.getTagIds()));
        return article;
    }

    @Override
    public Map<String, Object> getVoByCode(String code, Integer articleStatus) {
        ArticleVo article = iArticleDao.selectVoByCode(code, articleStatus);
        return convertArticleVo(article);
    }

    @Override
    public Map<String, Object> getVoByTitle(String articleTitle) {
        ArticleVo article = iArticleDao.selectVoByTitle(articleTitle,
                CommonConstant.ACTICLE_STATUS_BLOG);
        return convertArticleVo(article);
    }

    private Map<String,Object> convertArticleVo(ArticleVo article){
        if (article == null){
            return null;
        }
        article.setCountMessages(getArticleCountMessages(article.getId()));
        updateAritcleHits(article.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        article.setTagNames(getAllTagNamesBy(article.getTagIds()));
        Article preArticle = iArticleDao.selectNextOrPreVoBy(article, false);
        Article nextArticle = iArticleDao.selectNextOrPreVoBy(article, true);
        map.put("currentArticle", article);
        map.put("preArticle", preArticle);
        map.put("nextArticle", nextArticle);
        return map;
    }

    @Override
    public PageInfoResult<Article> getAllBy(Integer articleStatus,
                                            PageConfig pageConfig) {
        Article article = new Article();
        article.setStatus(articleStatus);
        List<Article> result = iArticleDao.selectBy(article, pageConfig);
        for(int i =0,len = result.size();i<len;i++){
            result.get(i).setCountMessages(getArticleCountMessages(result.get(i).getId()));
        }
        Integer resultCount = iArticleDao.selectCountBy(article);
        PageInfoResult<Article> pageInfoResult = new PageInfoResult(result,
                pageConfig, resultCount);
        return pageInfoResult;
    }

    @Override
    public PageInfoResult<ArticleVo> getAllManageBy(
            Integer articleStatus, PageConfig pageConfig) {
        Article article = new Article();
        article.setStatus(articleStatus);
        List<ArticleVo> result = iArticleDao.selectVoBy(article, pageConfig);
        Integer resultCount = iArticleDao.selectCountBy(article);
        PageInfoResult<ArticleVo> pageInfoResult = new PageInfoResult(result,
                pageConfig, resultCount);
        return pageInfoResult;
    }
    @Override
    public Map<String, Object> getAllByCategoryCode(String code, boolean status) {
        Category category = iCategoryDao.selectByCode(code, status);
        return convertArticleVos(category);
    }
    @Override
    public Map<String, Object> getAllByCategoryName(String categoryName,boolean status) {
        Category category = iCategoryDao.selectByName(categoryName,status);
        return convertArticleVos(category);
    }


    private Map<String,Object> convertArticleVos(Category category){
        Map<String, Object> map = new HashMap<>();
        CategoryVo categoryVo = new CategoryVo();
        map.put("category", categoryVo.categor2Vo(category));
        if (category != null) {
            Article article = new Article();
            article.setStatus(CommonConstant.ACTICLE_STATUS_BLOG);
            article.setCategoryIds(category.getId());
            List<ArticleVo> result = iArticleDao.selectVoBy(article, null);
            int countMessages=0,hits = 0;
            for(int i =0,len = result.size();i<len;i++){
                int messageCount = getArticleCountMessages(result.get(i).getId());
                result.get(i).setCountMessages(messageCount);
                hits+=result.get(i).getHits();
                countMessages+=result.get(i).getCountMessages();
            }
            categoryVo.setCountMessages(countMessages);
            categoryVo.setHits(hits);
            int counts = result == null ? 0 : result.size();
            categoryVo.setCounts(counts);
            map.put("listArticle", result);
        }
        return map;
    }

    @Override
    public List<Article> getAllByTagId(int tagId, Integer articleStatus) {
        Article article = new Article();
        article.setTagIds(tagId + ",");
        article.setStatus(articleStatus);
        List<Article> list = iArticleDao.selectBy(article, null);
        return list;
    }

    @Override
    public List<Article> getAllByCategoryId(int categoryId,
                                                   Integer articleStatus) {
        Article article = new Article();
        article.setCategoryIds(categoryId);
        article.setStatus(articleStatus);
        List<Article> list = iArticleDao.selectBy(article, null);
        return list;
    }

    @Override
    public List<ArticleVo> getAllByTagName(String tagName) {
        Tag tag = iTagDao.selectByName(tagName);
        if (tag != null) {
            Article article = new Article();
            article.setStatus(CommonConstant.ACTICLE_STATUS_BLOG);
            article.setTagIds(tag.getId() + ",");
            List<ArticleVo> result = iArticleDao.selectVoBy(
                    article, null);
            for(int i =0,len = result.size();i<len;i++){
                result.get(i).setCountMessages(getArticleCountMessages(result.get(i).getId()));
            }
            return result;
        }
        return null;
    }

    @Override
    public List<ArchiveVo> getAllArchiveVo(Integer articleStatus,
                                           String archiveTypeYear, String archiveTypeYearMonth) {
        return iArticleDao.selectAllArchives(articleStatus, archiveTypeYear, archiveTypeYearMonth);
    }

    @Override
    public List<Article> getAllArchiveArticles(String name, Integer articleStatus,
                                               String archiveType) {
        List<Article> list = iArticleDao.selectArchiveArticles(articleStatus, archiveType, name);
        for(int i =0,len = list.size();i<len;i++){
            list.get(i).setCountMessages(getArticleCountMessages(list.get(i).getId()));
        }
        return list;
    }

    private int getArticleCountMessages(int articleId){
        int count = iMessageDao.selectCountByArticleId(articleId);
        return count;
    }
    private void updateAritcleHits(int articleId){
        Article article = iArticleDao.selectById(articleId);
        article.setHits(article.getHits()+1);
        iArticleDao.updateHits(article);
    }

    private String[] getAllTagNamesBy(String tagIds) {
        String[] tagIdsArr = tagIds.split(",");
        if (tagIdsArr != null && tagIdsArr[0] != "") {
            String[] tagNames = new String[tagIdsArr.length];
            List<Integer> tagIdList = new ArrayList<Integer>(tagIdsArr.length);
            for (int i = 0; i < tagIdsArr.length; i++) {
                if (tagIdsArr[i] != "") {
                    tagIdList.add(Integer.parseInt(tagIdsArr[i]));
                }
            }
            List<Tag> tags = iTagDao.selectByIds(tagIdList);
            for (int i = 0; i < tags.size(); i++) {
                tagNames[i] = tags.get(i).getName();
            }
            return tagNames;
        }
        return null;
    }

    private String getTagIdsBy(String[] tagNames) {
        if (tagNames != null) {
            StringBuilder strBuilderTagIds = new StringBuilder();
            for (int i = 0; i < tagNames.length; i++) {
                if (tagNames[i] != null && tagNames[i].trim() != "") {
                    Tag t = iTagDao.selectByName(tagNames[i]);
                    if (t != null) {
                        strBuilderTagIds.append(t.getId() + ",");
                    } else {
                        Tag newTag = new Tag(tagNames[i]);
                        Integer r = iTagDao.insert(newTag);
                        if (r > 0) {
                            strBuilderTagIds.append(newTag.getId() + ",");
                        }
                    }
                }
            }
            return strBuilderTagIds.toString();
        }
        return null;
    }
}
