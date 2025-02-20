import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.trycatch.prography.data.datasource.PhotoRemoteDataSource
import com.trycatch.prography.data.model.PhotoEntity

class PhotoPagingSource(
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : PagingSource<Int, PhotoEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoEntity> {
        return try {
            val page = params.key ?: 1
            val response = photoRemoteDataSource.getPhotos(page, params.loadSize)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
} 